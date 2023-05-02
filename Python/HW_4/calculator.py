def is_float(input_string):
    try:
        float(input_string.strip())
        return True
    except ValueError:
        return False


def get_number(sentence_string):
    while True:
        user_input = input(sentence_string + " ")
        if is_float(user_input) and not user_input.upper().isupper():
            return float(user_input.strip())
        else:
            print("Invalid input. Try to provide a valid number.")


def get_operator(sentence_string):
    valid_operator = ("+", "-", "*", "/")
    while True:
        user_input = input(sentence_string + " ").strip()
        if user_input in valid_operator:
            return str(user_input)
        else:
            print("You may only enter one of the following operators: + - * /")


def halt(sentence_string):
    valid_true = ("Y", "y", "YES", "Yes", "yes")
    valid_false = ("N", "n", "NO", "No", "no")
    while True:
        user_input = input(sentence_string + " ").strip()
        if user_input in valid_true:
            return True
        elif user_input in valid_false:
            return False
        else:
            print("Invalid response. Please enter [Y|N].")


if __name__ == "__main__":
    # print(get_number("Please enter a number:"))
    # print(get_operator("Enter an orithmetic operator:"))
    # print(halt("Would you like to continue?"))
    pass
