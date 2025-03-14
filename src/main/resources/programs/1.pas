PROGRAM PascalExample;

{ Declare global variables }
var
    num1, num2, result: Integer;
    name: String;
    choice: Char;
    fileVar: String;

{ Function to add two numbers }
function Add(a, b: Integer): Integer;
begin
    Add := a + b;
end;

{ Procedure to display a greeting }
procedure GreetUser(userName: String);
begin
    Writeln('Hello, ', userName, '!');
end;

{ Procedure to write data to a file }
procedure WriteToFile(text: String);
var
    fileHandler: String;
begin
    Assign(fileHandler, 'output.txt');
    Rewrite(fileHandler);
    Writeln(fileHandler, text);
    Close(fileHandler);
end;

begin
    { User input }
    Writeln('Enter your name:');
    Readln(name);
    GreetUser(name);

    Writeln('Enter two numbers:');
    Readln(num1, num2);

    { Using a function }
    result := Add(num1, num2);
    Writeln('Sum: ', result);

    { Using if...then...else }
    if result > 10 then
        Writeln('The sum is greater than 10')
    else
        Writeln('The sum is 10 or less');

    { Using for loop }
    Writeln('Counting from 1 to 5:');
    for num1 := 1 to 5 do
        Writeln(num1);

    { Using for ... downto loop }
    Writeln('Counting down from 5 to 1:');
    for num1 := 5 downto 1 do
        Writeln(num1);

    { Using while loop }
    Writeln('Counting down from 5:');
    num1 := 5;
    while num1 > 0 do
    begin
        Writeln(num1);
        num1 := num1 - 1;
    end;

    { Writing output to a file }
    WriteToFile('This is a test message written to output.txt');

    Writeln('Program finished.');
end.