import pygame

class Platform:

    def __init__(self, x, y, w):
        self.x = x
        self.y = y
        self.w = w
        self.h = 50
        self.src = pygame.image.load('Images\\platform.png')
        self.src = pygame.transform.scale(self.src, (self.w, self.h))
