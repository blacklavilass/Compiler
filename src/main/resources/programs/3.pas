program ImplicitTypeConversion;
var
  intVar: Integer;
  realVar: Real;

procedure a();
begin
end;

begin
  intVar := 10;
  realVar := intVar;

  Writeln('Integer: ', intVar);
  Writeln('Real after casting: ', realVar);

  realVar := intVar * 3.14;
  Writeln('Result of multiplication after casting: ', realVar);
end.