define i32 @main() {
  %y= alloca i32
  %1= add i32 0 , 0
  store i32 %1, i32* %y
  br label %CondWhile0
CondWhile0:
  %2= load i32, i32* %y
  %3= add i32 0 , 1
  %4= icmp slt i32 %2, %3
  br i1 %4, label %While0, label %WhileEnd0
While0:
  %5= add i32 0 , 1
  store i32 %5, i32* %y
  br label %CondWhile0
WhileEnd0:
  %6= load i32, i32* %y
  call void @println(i32 %6)
ret i32 0
}
@.strP= private unnamed_addr constant [4 x i8] c"%d\0A\00", align 1
define void @println(i32 %var) {
  %1= call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.strP, i32 0, i32 0), i32 %var)
  ret void
}
declare i32 @printf(i8*, ...)
