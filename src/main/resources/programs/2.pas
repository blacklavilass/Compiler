PROGRAM ExampleProgram;

var
    x, y: Integer;
    message: String;

procedure PrintMessage;
begin
    Writeln('Hello, world!');
end;

procedure Add(a, b: Integer);
begin
    Writeln('Sum: ', a + b);
end;

var
    result: Integer;

procedure Multiply(a, b: Integer);
begin
    Writeln('Product: ', a * b);
end;

begin
    x := 10;
    y := 20;
    PrintMessage();
    Add(x, y);
    result := x * y;
    Multiply(x, y);
end.