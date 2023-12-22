define i32 @main() {
  %a= alloca i32
  %1= call i32 @readInt()
  store i32 %1, i32* %a
  %b= alloca i32
  %2= call i32 @readInt()
  store i32 %2, i32* %b
  br label %CondWhile0
CondWhile0:
  %3= add i32 0 , 0
  %4= load i32, i32* %b
  %5= icmp slt i32 %3, %4
  br i1 %5, label %While0, label %WhileEnd0
While0:
  %c= alloca i32
  %6= load i32, i32* %b
  store i32 %6, i32* %c
  br label %CondWhile1
CondWhile1:
  %7= load i32, i32* %b
  %8= load i32, i32* %a
  %9= add i32 0 , 1
  %10= add i32 %8, %9
  %11= icmp slt i32 %7, %10
  br i1 %11, label %While1, label %WhileEnd1
While1:
  %12= load i32, i32* %a
  %13= load i32, i32* %b
  %14= sub i32 %12, %13
  store i32 %14, i32* %a
  br label %CondWhile1
WhileEnd1:
  %15= load i32, i32* %a
  store i32 %15, i32* %b
  %16= load i32, i32* %c
  store i32 %16, i32* %a
  br label %CondWhile0
WhileEnd0:
  %17= load i32, i32* %a
  call void @println(i32 %17)
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
