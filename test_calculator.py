import pytest

from calculator import divide

# test suite:
def test_int_numbers():
    assert divide(10, 2) == 5

def test_float_numbers():
    assert divide(3.3, 10.11) == 3.3 / 10.11

def test_string_type_error():
    with pytest.raises(TypeError):
        divide("10", 2)

def test_bool_type_error():
    with pytest.raises(TypeError):
        divide(True, False)

def test_zero_division_error():
    with pytest.raises(ZeroDivisionError):
        divide(10, 0)

def test_negative_numbers():
    assert divide(-20, -32) == -20 / -32

def test_divide_float_precision():
    assert divide(1, 3) == pytest.approx(1/3)