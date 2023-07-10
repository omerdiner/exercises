import tkinter as tk
import time
from plyer import notification


def calculate_total_seconds(h, m, s):
    return h * 3600 + m * 60 + s

def take_input_from_user():
    hour = int(entry_hour.get())
    minute = int(entry_minute.get())
    second = int(entry_second.get())
    total_seconds = calculate_total_seconds(hour, minute, second)
    global countdown_seconds
    countdown_seconds = total_seconds
    input_screen.destroy()

def countdown():
    global countdown_seconds
    while countdown_seconds >= 0:
        hour = countdown_seconds // 3600
        minute = (countdown_seconds % 3600) // 60
        second = (countdown_seconds % 3600) % 60
        label["text"] = f"{hour:02d}:{minute:02d}:{second:02d}"
        countdown_seconds -= 1
        main_screen.update()
        time.sleep(1)
    label["text"] = "Countdown Completed!"

    notification.notify(
            title='INFO',
            message='Countdown completed!',
            timeout=5,
        )


input_screen = tk.Tk()
input_screen.title("Countdown Input")

label_hour = tk.Label(input_screen, text="Hour:", font=("Arial", 14))
label_hour.pack(pady=5)

entry_hour = tk.Entry(input_screen, font=("Arial", 14), justify="center")
entry_hour.pack(pady=5)

label_minute = tk.Label(input_screen, text="Minute:", font=("Arial", 14))
label_minute.pack(pady=5)

entry_minute = tk.Entry(input_screen, font=("Arial", 14), justify="center")
entry_minute.pack(pady=5)

label_second = tk.Label(input_screen, text="Second:", font=("Arial", 14))
label_second.pack(pady=5)

entry_second = tk.Entry(input_screen, font=("Arial", 14), justify="center")
entry_second.pack(pady=5)

button_start = tk.Button(input_screen, text="Start", command=take_input_from_user)
button_start.pack(pady=10)

input_screen.mainloop()

main_screen = tk.Tk()
main_screen.title("Countdown Application")

label = tk.Label(main_screen, font=("Arial", 24))
label.pack(pady=20)

countdown()

main_screen.mainloop()
