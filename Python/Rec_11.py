# Q1
def a(x):
    def b(y):
        return x+y
    return b

def increment(x):
    def addedBy(y):
        return x+y
    return addedBy

# Q2

def multiple(ranges, x):
    return filter(lambda n: n % x == 0, ranges)

def first(stringList):
    return map(lambda x: x[0] ,stringList)

#Q4
class series(object):
    def __init__(self, low, high):
        self.curr = low
        self.max = high
    def __iter__(self):
        return self
    def __next__(self):
        if(self.curr > self.max ):
            raise StopIteration
        self.curr = self.curr+1
        return self.curr - 1

def main():
    print()

if __name__ == "__main__":
    main()