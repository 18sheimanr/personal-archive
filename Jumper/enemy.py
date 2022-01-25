import pygame

class enemy:

    def __init__(self, x, y, p):
        self.health = 3
        self.w = 50
        self.h = 50
        self.x = x
        self.y = y
        self.plat = p
        self.src = pygame.image.load("Images\\enemy.png")
