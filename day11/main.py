from collections import deque
from tqdm import tqdm
from functools import reduce
from enum import Enum
from math import floor


class Operation(Enum):
    ADD = "+"
    MULTIPLY = "*"


class Monkey:
    def __init__(self):
        self.items = deque()
        self.op = None
        self.op_operand = None

        self.test_operand = None
        self.true_recipient = None
        self.false_recipient = None

        self.inspect_count = 0

    def construct(self):
        input()  # throw away the monkey header line

        # handle items
        starting_items = input()
        starting_items = starting_items[starting_items.find(":")+1:]
        for item in starting_items.split(","):
            self.items.append(int(item))

        # operation
        operation = input().strip()
        op_type = operation[21]
        if op_type == "+":
            self.op = Operation.ADD
        elif op_type == '*':
            self.op = Operation.MULTIPLY
        else:
            assert False
        operation = operation[22:]
        self.op_operand = operation.strip()  # this may be numerical, or may be 'old'

        # division test
        self.test_operand = int(input().strip()[19:])

        # true branch
        self.true_recipient = int(input().strip()[24:])

        # false branch
        self.false_recipient = int(input().strip()[25:])

        # validation
        for prop in [self.op, self.op_operand, self.test_operand, self.true_recipient, self.false_recipient]:
            assert prop is not None
        return self

    def play(self, monkeys, lcm):
        count = len(self.items)
        self.inspect_count += count
        for _ in range(count):
            old_worry = self.items.popleft()
            operand = old_worry if self.op_operand == "old" else int(
                self.op_operand)
            new_worry = old_worry + operand if self.op == Operation.ADD else old_worry * operand
            new_worry = new_worry % lcm

            test_result = new_worry % self.test_operand == 0
            recipient = self.true_recipient if test_result else self.false_recipient
            monkeys[recipient].items.append(new_worry)


def main():
    monkeys = []
    while True:
        monkeys.append(Monkey().construct())
        try:
            input()
        except Exception:
            break

    # a % m === (a % km) % m
    lcm = reduce(lambda aggr, m2: aggr * m2.test_operand, monkeys, 1)

    for _ in tqdm(range(10000)):
        for monkey in monkeys:
            monkey.play(monkeys, lcm)

    maxs = [m.inspect_count for m in monkeys]
    maxs.sort()
    print(maxs[-1] * maxs[-2])


main()
