def divide(numerator, denominator):
    if not isinstance(numerator, (int, float)) or not isinstance(denominator, (int, float)):
        raise TypeError("Inputs must be numeric")
    
    if isinstance(numerator, bool) or isinstance(denominator, bool):
        raise TypeError("Inputs cannot be booleans, must be numeric")
    
    if denominator == 0:
        raise ZeroDivisionError("Denominator cannot be 0")
    
    return numerator / denominator