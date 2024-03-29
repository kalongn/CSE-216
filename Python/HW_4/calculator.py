class Calculator:

    __first_number = 0
    __second_number = 0
    __operator = ""
    __result = 0.0

    def __init__(self, first_numer_prompt: str = "Enter the first number:", arithmetic_operator_prompt: str = "Enter the operator:", second_number_prompt: str = "Enter the second number:", goodbye_messg: str = "Bye!", halt_prompt: str = "Would you like to continue?") -> None:
        self.__first_numer_prompt = first_numer_prompt
        self.__arithmetic_operator_prompt = arithmetic_operator_prompt
        self.__second_number_prompt = second_number_prompt
        self.__goodbye_messg = goodbye_messg
        self.__halt_prompt = halt_prompt
        self.__calculate_history = []

    @staticmethod
    def is_float(input_string: str) -> bool:
        try:
            float(input_string.strip())
            return True
        except ValueError:
            return False

    @staticmethod
    def is_float_an_integer(input_string: str) -> bool:
        if Calculator.is_float(input_string.strip()):
            return float(input_string.strip()).is_integer()
        return False

    @staticmethod
    def get_number(sentence_string: str) -> int:
        while True:
            user_input = input(sentence_string + " ")
            if Calculator.is_float_an_integer(user_input) and not user_input.islower():
                return int(float(user_input.strip()))
            else:
                print("Invalid input. Try to provide a valid number.")

    @staticmethod
    def get_operator(sentence_string: str) -> str:
        valid_operator = ("+", "-", "*", "/")
        while True:
            user_input = input(sentence_string + " ").strip()
            if user_input in valid_operator:
                return str(user_input)
            else:
                print("You may only enter one of the following operators: + - * /")

    @staticmethod
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

    @classmethod
    def calculate(cls, first_num_prompt: str = "Enter the first number:", arithmetic_operator_prompt: str = "Enter the operator:", second_num_prompt: str = "Enter the second number:") -> float:
        first_number = cls.get_number(first_num_prompt)
        arithmetic_operator = cls.get_operator(
            arithmetic_operator_prompt)
        second_number = cls.get_number(second_num_prompt)
        if (arithmetic_operator == "/" and second_number == 0):
            raise ZeroDivisionError("Cannot divide by 0.")
        result = float(
            eval(f'{first_number} {arithmetic_operator} {second_number}'))
        cls.__first_number = first_number
        cls.__second_number = second_number
        cls.__operator = arithmetic_operator
        cls.__result = result
        return result

    def run(self) -> None:
        while True:
            result = Calculator.calculate(
                self.__first_numer_prompt, self.__arithmetic_operator_prompt, self.__second_number_prompt)
            self.__calculate_history.append(result)
            print(str(self.__first_number) + " " + str(self.__operator) +
                  " " + str(self.__second_number) + " = " + str(float(self.__result)))
            if (not Calculator.halt(self.__halt_prompt)):
                break
        print(
            f"You carried out {len(self.__calculate_history)} calculations. The results were: {'; '.join(str(result) for result in self.__calculate_history)}")
        if (self.__goodbye_messg):
            print(str(self.__goodbye_messg))


if __name__ == "__main__":
    calc_one = Calculator()
    calc_one.run()
