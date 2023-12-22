define i32 @main() {
  %1= add i32 0 , 1
  %2= add i32 0 , 1
  %3= icmp eq i32 %1, %2
  br i1 %3, label %if0, label %Else0
if0:
  %x= alloca i32
  %4= add i32 0 , 0
  store i32 %4, i32* %x
  br label %EndIf0
Else0:
  br label %EndIf0
EndIf0:
ret i32 0
}
