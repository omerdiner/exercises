import tkinter as tk

LB_TO_KG = 0.45359237
FT_TO_M = 0.3048
INC_TO_M = 0.0254
M_TO_CM = 100

bmi_categories = {
    (0, 15): "Very severely underweight",
    (15, 16): "Severely underweight",
    (16, 18.5): "Underweight",
    (18.5, 25): "Normal (healthy) weight",
    (25, 30): "Overweight",
    (30, 35): "Obese Class I (Moderate)",
    (35, 40): "Obese Class II (Severe)",
    (40, float('inf')): "Obese Class III (Very severe or morbidly obese)"
}

screen = tk.Tk()
screen.title("BMI Calculator")
screen.config(padx=20, pady=20)


result_label = tk.Label()
result_label.grid(column=0, row=3)
result_label.config(font=("Arial", 24, "bold"))

def get_bmi_category(bmi):
    for bmi_range, category in bmi_categories.items():
        if bmi_range[0] <= bmi < bmi_range[1]:
            return category

def check_input(x):
    try:
        x = float(x)
        if x > 0:
            return True
        else:
            return False
    except ValueError:
        return False


# check if radio button selected
def check_unit():
    if unit_choice.get() == 0:
        return False
    else:
        return True


def calculate_bmi():

    height = height_entry.get()
    weight = weight_entry.get()
    unit = unit_choice.get()

    #input validation
    if not check_unit():
        result_label.config(text="Please select unit.")
        return
    if not check_input(height):
        result_label.config(text="Invalid height.")
        return
    if not check_input(weight):
        result_label.config(text="Invalid weight.")
        return

    height = float(height)
    weight = float(weight)

    #convert lb/ft to kg/m
    if unit == 2:
        feet = float(str(height).split(".")[0])
        inches = float(str(height).split(".")[1])
        height = ((feet * FT_TO_M) + (inches * INC_TO_M))* M_TO_CM
        weight = weight * LB_TO_KG


    height = height / 100
    bmi = weight / (height ** 2)
    bmi = round(bmi, 2)
    result_label.config(background="light blue",text=f"Your BMI is {bmi}\n"
                             f"Your category is {get_bmi_category(bmi)}")


# label for height
height_label = tk.Label(text="Height")
height_label.grid(column=0, row=0)
height_label.config(font=("Arial", 24, "bold"))
height_label.config(padx=10, pady=10)

# label for weight
weight_label = tk.Label(text="Weight")
weight_label.config(font=("Arial", 24, "bold"))
weight_label.config(padx=10, pady=10)
weight_label.grid(column=0, row=1)

# entry for height
height_entry = tk.Entry(width=10)
height_entry.grid(column=1, row=0)
height_entry.config(font=("Arial", 24, "bold"))
height_label.focus()

# entry for weight
weight_entry = tk.Entry(width=10)
weight_entry.grid(column=1, row=1)
weight_entry.config(font=("Arial", 24, "bold"))

unit_choice = tk.IntVar()
# radio button for kg/cm
kg_cm = tk.Radiobutton(text="kg/cm", value=1, variable=unit_choice)
kg_cm.grid(column=2, row=0)

# radio button for lb/in
lb_ft = tk.Radiobutton(text="lb/ft", value=2, variable=unit_choice)
lb_ft.grid(column=2, row=1)

# button
calculate_button = tk.Button(text="Calculate", command=calculate_bmi)
calculate_button.grid(column=1, row=2)
calculate_button.config(font=("Arial", 24, "bold"))


screen.mainloop()
