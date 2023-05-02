def is_float(input_string: str) -> bool:
    try:
        float(input_string.strip())
        return True
    except ValueError:
        return False


def get_number(sentence_string: str) -> float:
    while True:
        user_input = input(sentence_string + " ")
        if is_float(user_input) and not user_input.upper().isupper():
            return float(user_input.strip())
        else:
            print("Invalid input. Try to provide a valid number.")


def get_operator(sentence_string: str) -> str:
    valid_operator = ("+", "-", "*", "/")
    while True:
        user_input = input(sentence_string + " ").strip()
        if user_input in valid_operator:
            return str(user_input)
        else:
            print("You may only enter one of the following operators: + - * /")


def halt(sentence_string: str) -> bool:
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


def calculate() -> float:
    first_number = get_number("Enter the first number:")
    arithmetic_operator = get_operator("Enter the operator:")
    second_number = get_number("Enter the second number:")
    if (arithmetic_operator == "/" and second_number == 0.0):
        raise ValueError("Cannot divide by 0.")
    return eval(f'{first_number} {arithmetic_operator} {second_number}')


class Calculator:
    def __init__(self, first_numer_prompt: str, arithmetic_operator_prompt: str, second_number_prompt: str, goodbye_messg=None, halt_prompt: str = "Would you like to continue?") -> None:
        self.__first_numer_prompt = first_numer_prompt
        self.__arithmetic_operator_prompt = arithmetic_operator_prompt
        self.__second_number_prompt = second_number_prompt
        self.__goodbye_messg = goodbye_messg
        self.__halt_prompt = halt_prompt
        self.__calculate_history = []

    def run(self) -> None:
        while True:
            first_number = get_number(self.__first_numer_prompt)
            arithmetic_operator = get_operator(
                self.__arithmetic_operator_prompt)
            second_number = get_number(self.__second_number_prompt)
            if (arithmetic_operator == "/" and second_number == 0.0):
                raise ValueError("Cannot divide by 0.")
            result = eval(
                f'{first_number} {arithmetic_operator} {second_number}')
            self.__calculate_history.append(result)
            print(str(first_number) + " " + str(arithmetic_operator) +
                  " " + str(second_number) + " = " + str(result))
            if (not halt(self.__halt_prompt)):
                break
        print(
            f"You carried out {len(self.__calculate_history)} calculations. The results were: {'; '.join(str(result) for result in self.__calculate_history)}")
        if (self.__goodbye_messg):
            print(str(self.__goodbye_messg))


if __name__ == "__main__":
    # print(get_number("Please enter a number:"))
    # print(get_operator("Enter an orithmetic operator:"))
    # print(halt("Would you like to continue?"))
    # print(calculate())
    calc_one = Calculator("Enter the first number:",
                          "Enter the operator:", "Enter the second number:", "Bye!")
    calc_one.run()
