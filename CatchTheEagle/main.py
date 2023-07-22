import turtle
import time
import random

score=0
time_limit=0
level_dict={"hard":8,"medium":5,"easy":3}

game_screen=turtle.Screen()
game_screen.title("Catching the Eagle Game")
game_screen.bgcolor("white")

difficulty_input=game_screen.textinput("Choose level", "Hard , Medium or Easy?").lower()
time_input=game_screen.textinput("Choose time", "How many seconds do you want to play?")

try:
    time_limit=int(time_input)
except:
    time_limit=10

try:
    speed=level_dict[difficulty_input]
except:
    speed=level_dict["medium"]

screen_height=game_screen.window_height()
screen_width=game_screen.window_width()

eagle_instance=turtle.Turtle()
game_screen.addshape("Eagle.gif")
eagle_instance.shape("Eagle.gif")
eagle_instance.penup()
eagle_instance.speed(speed)



def random_move():
    x=random.randint(-screen_width/2,screen_width/2)
    y=random.randint(-screen_height/2,screen_height/2)
    eagle_instance.goto(x, y)

def update_score(score):
    score_turtle.clear()
    score_turtle.penup()
    score_turtle.hideturtle()
    score_turtle.goto(0, 250)
    score_turtle.write("Score: {}".format(score), align="center", font=("Courier", 24, "normal"))

def click_gif(x,y):
    random_move()
    global score
    score+=1
    update_score(score)


eagle_instance.onclick(click_gif)

def game_over():
    game_over_turtle=turtle.Turtle()
    game_over_turtle.color("red")
    game_over_turtle.penup()
    game_over_turtle.hideturtle()
    game_over_turtle.goto(0,0)
    game_over_turtle.write("Game Over",align="center",font=("Courier",34,"bold"))

def remaining_time(starting_time):
    return time_limit-(time.time()-starting_time)

starting_time = time.time()

score_turtle = turtle.Turtle()
score_turtle.color("black")
score_turtle.clear()
score_turtle.penup()
score_turtle.hideturtle()
score_turtle.goto(0, 250)
score_turtle.write("Score: {}".format(score), align="center", font=("Courier", 24, "normal"))

while (remaining_time(starting_time)) >0:
    random_move()

eagle_instance.hideturtle()

game_over()

turtle.mainloop()


