define i32 @main() {
  %x= alloca i32
  %1= add i32 0 , 1
  store i32 %1, i32* %x
  %y= alloca i32
  %2= add i32 0 , 1
  store i32 %2, i32* %y
  %z= alloca i32
  %3= add i32 0 , 4
  store i32 %3, i32* %z
  br label %CondWhile0
CondWhile0:
  %4= load i32, i32* %x
  %5= add i32 0 , 1
  %6= icmp eq i32 %4, %5
  %7= load i32, i32* %y
  %8= add i32 0 , 2
  %9= icmp slt i32 %7, %8
  %10= add i32 0 , 3
  %11= load i32, i32* %z
  %12= icmp slt i32 %10, %11
  %13= and i1 %9, %12
  %14= or i1 %6, %13
  br i1 %14, label %While0, label %WhileEnd0
While0:
  %15= add i32 0 , 0
  store i32 %15, i32* %x
  %16= add i32 0 , 3
  store i32 %16, i32* %z
  br label %CondWhile0
WhileEnd0:
  %17= load i32, i32* %z
  call void @println(i32 %17)
ret i32 0
}
@.strP= private unnamed_addr constant [4 x i8] c"%d\0A\00", align 1
define void @println(i32 %var) {
  %1= call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.strP, i32 0, i32 0), i32 %var)
  ret void
}
declare i32 @printf(i8*, ...)
