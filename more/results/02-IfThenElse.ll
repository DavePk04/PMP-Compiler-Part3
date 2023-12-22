define i32 @main() {
  %x= alloca i32
  %1= call i32 @readInt()
  store i32 %1, i32* %x
  %2= add i32 0 , 1
  %3= add i32 0 , 1
  %4= icmp eq i32 %2, %3
  br i1 %4, label %if0, label %Else0
if0:
  %5= add i32 0 , 0
  store i32 %5, i32* %x
  br label %EndIf0
Else0:
  %6= add i32 0 , 1
  store i32 %6, i32* %x
  br label %EndIf0
EndIf0:
  %7= load i32, i32* %x
  call void @println(i32 %7)
ret i32 0
}
@.strR= private unnamed_addr constant [3 x i8] c"%d\00", align 1
define i32 @readInt() {
  %var= alloca i32, align 4
  %1= call i32 (i8*, ...) @scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.strR, i32 0, i32 0), i32* %var)
  %2= load i32, i32* %var, align 4
  ret i32 %2
}
declare i32 @scanf(i8*, ...)
@.strP= private unnamed_addr constant [4 x i8] c"%d\0A\00", align 1
define void @println(i32 %var) {
  %1= call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.strP, i32 0, i32 0), i32 %var)
  ret void
}
declare i32 @printf(i8*, ...)
