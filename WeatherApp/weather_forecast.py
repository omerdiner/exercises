import tkinter as tk
import requests

API_KEY = "" # Your API key. You can get one from https://openweathermap.org/api

def get_weather_data():
    city = city_entry.get()

    url = f"http://api.openweathermap.org/data/2.5/weather?q={city}&appid={API_KEY}&units=metric&lang=tr"

    try:
        response = requests.get(url)
        data = response.json()

        coord = data["coord"]
        main = data["main"]
        wind = data["wind"]

        result_text.config(text=f"City: {city}\n"
                                f"Coordinates: {coord['lat']},{coord['lon']}\n"
                                f"Temp: {main['temp']}°C\n"
                                f"Humidity: {main['humidity']}%\n"
                                f"Wind speed: {(wind['speed']*3.6):.2f} km/h")
    except Exception as e:
        result_text.config(text="Error. Please try again.")

window = tk.Tk()
window.title("Weather")

window.geometry("300x200")

city_label = tk.Label(window, text="City name:")
city_label.pack(pady=10)

city_entry = tk.Entry(window)
city_entry.pack(pady=5)

result_text = tk.Label(window, text="", wraplength=300)
result_text.pack(pady=10)

get_weather_button = tk.Button(window, text="Get Info", command=get_weather_data)
get_weather_button.pack()

window.mainloop()
