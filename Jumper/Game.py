import pygame

class Game:
    
    def __init__ (self):
        self.platforms = []
        self.difficulty = 1.5
        self.gravity = 1
        self.running = True 
        self.fps = 60
        self.bg_src = pygame.image.load('Images\\bg.png')
        self.clock = pygame.time.Clock()
        self.width = 680
        self.height = 480
        self.enemies = []
        self.potions = []
        self.popup = False
        pygame.font.init()
        font = pygame.font.SysFont('Segoe UI', 20)
        self.pause_text = font.render("Paused - click START to resume, A to quit", 1, (255, 255, 255))
        self.health_text = font.render("Player Health", 1, (255, 255, 255))
