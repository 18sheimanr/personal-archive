import pygame

class potion:

    def __init__(self, x, y):
        self.x = x
        self.y = y
        self.w = 50
        self.h = 50
        self.src = pygame.image.load("Images\\potion.png")
        
        
