define i32 @main() {
  %x= alloca i32
  %1= add i32 0 , 1
  store i32 %1, i32* %x
  %y= alloca i32
  %2= add i32 0 , 2
  store i32 %2, i32* %y
  %z= alloca i32
  %3= add i32 0 , 3
  store i32 %3, i32* %z
  br label %CondWhile0
CondWhile0:
  %4= add i32 0 , 0
  %5= load i32, i32* %x
  %6= icmp slt i32 %4, %5
  %7= load i32, i32* %y
  %8= load i32, i32* %z
  %9= icmp slt i32 %7, %8
  %10= or i1 %6, %9
  br i1 %10, label %While0, label %WhileEnd0
While0:
  %11= add i32 0 , 1
  %12= mul i32  -1 , %11
  store i32 %12, i32* %x
  %13= add i32 0 , 2
  %14= mul i32  -1 , %13
  store i32 %14, i32* %z
  br label %CondWhile0
WhileEnd0:
  %15= load i32, i32* %x
  call void @println(i32 %15)
ret i32 0
}
@.strP= private unnamed_addr constant [4 x i8] c"%d\0A\00", align 1
define void @println(i32 %var) {
  %1= call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.strP, i32 0, i32 0), i32 %var)
  ret void
}
declare i32 @printf(i8*, ...)
