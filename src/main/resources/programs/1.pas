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
    result := a + b;
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
    Writeln(fileHandler, text);
end;

begin

    Writeln('Enter your name:');
    GreetUser(name);

    Writeln('Enter two numbers:');


    result := Add(num1, num2);
    Writeln('Sum: ', result);


    if result > 10 then
        Writeln('The sum is greater than 10')
    else
        Writeln('The sum is 10 or less');


    Writeln('Counting from 1 to 5:');
    for num1 := 1 to 5 do
        Writeln(num1);


    Writeln('Counting down from 5 to 1:');
    for num1 := 5 downto 1 do
        Writeln(num1);


    Writeln('Counting down from 5:');
    num1 := 5;
    while num1 > 0 do
    begin
        Writeln(num1);
        num1 := num1 - 1;
    end;


    WriteToFile('This is a test message written to output.txt');

    Writeln('Program finished.');

end.