def f(x: float) -> float:
    """Исследуемая функция f(x) = x^3 - x - 2."""
    return x ** 3 - x - 2

def bisection(a: float, b: float, eps: float = 0.0001) -> float:
    """Метод бисекции (деления отрезка пополам)."""
    if f(a) * f(b) > 0:
        raise ValueError("На концах отрезка функция должна иметь разные знаки!")

    iteration = 0
    while (b - a) / 2 > eps:
        c = (a + b) / 2
        iteration += 1
        print(f"Итерация {iteration}: a = {a:.6f}, b = {b:.6f}, c = {c:.6f}, f(c) = {f(c):.6f}")
        if f(a) * f(c) < 0:
            b = c
        else:
            a = c

    root = (a + b) / 2
    print(f"\nВсего итераций: {iteration}")
    return root

if __name__ == "__main__":
    a, b = 1.0, 2.0
    eps = 0.0001
    root = bisection(a, b, eps)
    print(f"Приближённый корень уравнения x^3 - x - 2 = 0 на отрезке [{a}; {b}]:")
    print(f"x ≈ {root:.6f}")
    print(f"Проверка: f(x) = {f(root):.8f}")
