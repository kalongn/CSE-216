import copy

# Q1
# This reverses the order of the tuple.
m = ("string", 12, 54)
m[::-1]

# Q2
# Tuples are immutable data structures in Python. and this line will throw an error.
# m[-1] = 'apple'

# Q3
# 1. Created a tuple of List 1,2,3; “sample” string, List 4,5,6
# 2. List 4,5,6 appended 4 and become List 4,5,6,4
# 3. n= first item in the tuple which is List 1,2,3
# 4. n.append 7 means the list is now 1,2,3,7
# 5. Adding 2 list together return a new list and become List 1,2,3,7,4,5,6,4
m = [1, 2, 3], "sample", [4, 5, 6]
m[-1].append(4)
n = m[0]
n.append(7)
print(n + m[-1])

# Q4
list1 = [1, 2, [3, 5], 4]
list2 = copy.copy(list1)
list3 = copy.deepcopy(list1)
list1[2][1] = 0

# Q5
string = "banana"
s = dict()
for i in string:
    if i not in s:
        s[i] = 1
    else:
        s[i] += 1

max = float("-inf")
c = ''
for i in s:
    if max < s[i]:
        max = s[i]
        c = i

print(c + "," + str(max))
