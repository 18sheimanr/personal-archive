from Game import *
from Player import *
from Platform import *
from enemy import *
from powerups import *
from random import randint
import sys, pygame, os, time

def initiate():
    pygame.init()
    pygame.display.set_caption("Dungeon Jump")
    pygame.display.flip()
    newPlat = Platform(150, game.height-100, 150)
    game.platforms.append(newPlat)
    for i in range(4):
        new_platform()
    player.x = game.platforms[2].x
    player.y = game.platforms[2].y-player.height

def new_platform():
    if game.platforms[len(game.platforms)-1].x < 150:
        overlap = randint(80, 250)
    elif game.platforms[len(game.platforms)-1].x > game.width-350:
        overlap = randint(-250, -80)
    else:
        chance = randint(0, 100)
        if chance > 50:
            overlap = randint(-250, -80)
        else:
            overlap = randint(80, 250)
                
    newPlat = Platform(game.platforms[len(game.platforms)-1].x+overlap, game.platforms[len(game.platforms)-1].y-170, randint(100, 200))
    game.platforms.append(newPlat)
    if newPlat.w > 160:
        newEnem = enemy(newPlat.x+0.5*newPlat.w-25, newPlat.y-50, newPlat) #choose wether or not to put on an enemy/potion based on width
        game.enemies.append(newEnem)
    elif newPlat.w < 110:
        newPot = potion(newPlat.x+0.5*newPlat.w-25, newPlat.y-50)
        game.potions.append(newPot)
        
def check_collisions():
    
    for p in game.platforms:

        if player.x+player.vx+player.width > p.x and player.x+player.vx < p.x+p.w and player.y < p.y+p.h and player.y+player.height > p.y:
            #player.vx = 0
            if player.x < p.x:
                player.x = p.x-player.width-player.vx #minus player.vx because this is a detection of a collision after adding it-simple AlgeBREH
            else:
                player.x = p.x+p.w-player.vx
            
        if player.y+player.vy+player.height > p.y and player.y+player.vy < p.y+p.h and player.x < p.x+p.w and player.x+player.width > p.x:
            player.vy = 0
            if player.y < p.y:
                player.y = p.y-player.height
            else:
                player.y = p.y+p.h
                
def update_platforms():
    for p in game.platforms:
        p.y += game.difficulty
        if p.y > game.height+200:
            new_platform()
            game.platforms.remove(p)

def update_enemies():
    for e in game.enemies:
        e.y += game.difficulty
        if player.y+player.vy+player.height > e.y and player.y < e.y and player.x < e.x+e.w and player.x+player.width > e.x:
            e.health -= 1
            player.vy = -12
            player.y = e.y-player.height
        if player.x < e.x+e.w and player.x+player.width > e.x and player.y < e.y+e.h and player.y+player.height > e.y:
            player.health -= 0.2
            player.score -= 1
        if player.x >= e.x+e.w and e.x+e.w < e.plat.x+e.plat.w and player.y < e.plat.y:
            e.x += 1.5
        if player.x+player.width <= e.x and e.x > e.plat.x and player.y < e.plat.y:
            e.x -= 1.5

        if e.health == 0:
            game.enemies.remove(e)
            player.score += 10

        elif e.y > game.height:
            game.enemies.remove(e)

def update_powerups():
    for w in game.potions:
        w.y += game.difficulty
        if player.y < w.y+w.h and player.y+player.height > w.y and player.x < w.x+w.w and player.x+player.width > w.x:
            player.health += 2
            if player.health > 10:
                player.health = 10
            game.potions.remove(w)
        if w.y > game.height:
            game.potions.remove(w)

def draw_game():
    pygame.draw.rect(screen, [100, 60, 15], (0, 0, game.width, game.height))

    for p in game.platforms:
        screen.blit(p.src, (p.x, p.y))

    for e in game.enemies:
        screen.blit(e.src, (e.x, e.y))

    for w in game.potions:
        screen.blit(w.src, (w.x, w.y))
        
    pygame.draw.rect(screen, [0,0,150], (player.x, player.y, player.width, player.height), 0)
    pygame.draw.rect(screen, [210, 0, 0], (0, 0, player.health*(game.width/10), 30))
    screen.blit(game.health_text, (20, 0))
    temp_score_text = font.render("Score: "+str(round(player.score)), 1, (255, 255, 255))
    screen.blit(temp_score_text, (game.width-120, 0))
    pygame.display.flip()

def check_inputs():
    for event in pygame.event.get():
        pressed = pygame.key.get_pressed()
        if pressed[pygame.K_LEFT]:
            player.vx = -5
        elif pressed[pygame.K_RIGHT]:
            player.vx = 5
        else:
            player.vx = 0
        if pressed[pygame.K_ESCAPE]:
            game.running = False
        elif pressed[pygame.K_UP] and player.vy == 0:
            player.vy = -20
        if event.type == pygame.QUIT:
            pygame.quit()
            sys.exit()

def check_loser():
    if player.y+player.height > game.height or player.health < 0:
        game.popup = True
        game.running = False
        pygame.draw.rect(screen, [255,0,0], (0, 0, game.width, game.height), 0)
        pygame.display.flip()
        time.sleep(0.5)
        temp = font_large.render("You lose", 1, (0, 0, 0))
        screen.blit(temp, (100, 100))
        pygame.display.flip()

def update():       #main update function

    check_inputs()
    
    player.vy += game.gravity
    player.score += 0.1

    check_collisions() #this is based on previous frames player.(v)x and player.(v)y, so incremeting players location must be AFTER this

    player.x += player.vx
    player.y += player.vy
    player.y += game.difficulty
    
    update_platforms()
            
    update_enemies()

    update_powerups()

    draw_game()

    check_loser()

game = Game()
player = Player()
screen = pygame.display.set_mode([game.width, game.height])
font = pygame.font.SysFont('Segoe UI', 20)
font_large = pygame.font.SysFont('Segoe UI', 80)
initiate()

while(1):
    while game.running == True:
        update()
        game.clock.tick(game.fps)
    while game.running == False:
        game.clock.tick(game.fps/2)
        if game.popup == False:
            screen.blit(game.pause_text, (20, 20))
            pygame.display.flip()
        for event in pygame.event.get():
            keystate = pygame.key.get_pressed()
            if event.type == pygame.QUIT:
                pygame.quit()
                sys.exit()
            if keystate[pygame.K_ESCAPE] and game.popup == False:
                game.running = True
            elif keystate[pygame.K_a]:
                pass #quit game
