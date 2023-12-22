define i32 @main() {
  %w= alloca i32
  %1= add i32 0 , 0
  store i32 %1, i32* %w
  %x= alloca i32
  %2= add i32 0 , 1
  store i32 %2, i32* %x
  %y= alloca i32
  %3= add i32 0 , 2
  store i32 %3, i32* %y
  br label %CondWhile0
CondWhile0:
  %4= load i32, i32* %w
  %5= load i32, i32* %x
  %6= icmp slt i32 %4, %5
  br i1 %6, label %While0, label %WhileEnd0
While0:
  br label %CondWhile1
CondWhile1:
  %7= load i32, i32* %x
  %8= load i32, i32* %y
  %9= icmp slt i32 %7, %8
  br i1 %9, label %While1, label %WhileEnd1
While1:
  br label %CondWhile2
CondWhile2:
  %10= load i32, i32* %y
  %11= add i32 0 , 3
  %12= icmp slt i32 %10, %11
  br i1 %12, label %While2, label %WhileEnd2
While2:
  %13= add i32 0 , 3
  store i32 %13, i32* %y
  %14= load i32, i32* %y
  store i32 %14, i32* %x
  %15= load i32, i32* %y
  store i32 %15, i32* %w
  br label %CondWhile2
WhileEnd2:
  br label %CondWhile1
WhileEnd1:
  br label %CondWhile0
WhileEnd0:
  %16= load i32, i32* %w
  call void @println(i32 %16)
ret i32 0
}
@.strP= private unnamed_addr constant [4 x i8] c"%d\0A\00", align 1
define void @println(i32 %var) {
  %1= call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.strP, i32 0, i32 0), i32 %var)
  ret void
}
declare i32 @printf(i8*, ...)
