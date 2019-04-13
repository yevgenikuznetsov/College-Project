from builtins import list, tuple, str, staticmethod


def q1_a(list_temp):
    total = 0
    for i in range(len(list_temp)):
        total = total + sum(list_temp[i])
    return total


def q1_b(list_temp):
    return sum([sum(x) for x in list_temp])


def q2_a(string_temp):
    return ''.join(reversed(string_temp))


def q2_b(list_temp):
    for i in range(len(list_temp)):
        if type(list_temp[i]) == list:
            list_temp[i].reverse()
    list_temp.reverse()
    return list_temp


def q2_c(tuple_list):
    tuple_list = list(tuple_list)
    tuple_list.reverse()
    return tuple(tuple_list)


def q3_a(tuple_list):
    set_temp = {}

    for i in tuple_list:
        set_temp = i.union(set_temp)

    char = sorted(set(filter(lambda item: type(item) == str, set_temp)))
    number = sorted(set(filter(lambda item: type(item) == int, set_temp)))
    flo = sorted(set(filter(lambda item: type(item) == float, set_temp)))
    return char + sorted(number + flo)


def q3_b(list_temp):
    return {i: list_temp[i] for i in range(len(list_temp))}


def q6_a():
    x = int(input("Please enter a number "))
    y = x > 10 and 6 or 9
    return y


def q6_b():
    x = int(input("Please enter a number "))
    y = 6 if x > 10 else 9
    return y


def q7_a():
    temp = []
    x = int(input("Please enter a first number "))
    y = int(input("Please enter a second number "))

    for i in range(x, y+1):
        i % 2 == 0 and temp.append(i) or i+1

    print_list(temp)


def print_list(list_temp):

    for i in range(len(list_temp)):
        print(list_temp[len(list_temp) - i - 1], end=' ')
    print()

    for i in range(len(list_temp)):
        print(list_temp[i], end=' ')
    print()


def q7_b():
    list_from_while = []
    i = 0
    x = int(input("Please enter a first number "))
    y = int(input("Please enter a second number "))

    while y >= x:
        x % 2 == 0 and list_from_while.append(x)
        x = x + 1

    while i < len(list_from_while):
        print(list_from_while[len(list_from_while) - i - 1], end=' ')
        i = i + 1
    print()
    i = 0
    while i < len(list_from_while):
        print(list_from_while[i], end=' ')
        i = i + 1


temp_list = [[12, 4], 1, [2, 3]]
temp_list1 = [[12, 4], [1], [2, 3]]
s = 'name'
temp_tuple = (1, 2, 3)
temp_tupleWithSet = ({'a', 10, 5}, {'c', 5, 'b', 6}, {5.5, 5.4, 7})

print(q1_a(temp_list1))
print(q1_b(temp_list1))
print(q2_a(s))
print(q2_b(temp_list1))
print(q2_c(temp_tuple))

new_list = q3_a(temp_tupleWithSet)
print(new_list)

new_list = q3_b(new_list)
print(new_list)

print(q6_a())
print(q6_b())
q7_a()
q7_b()

