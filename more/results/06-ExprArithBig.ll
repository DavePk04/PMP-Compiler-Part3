define i32 @main() {
  %x= alloca i32
  %1= add i32 0 , 7
  store i32 %1, i32* %x
  %2= load i32, i32* %x
  %3= add i32 0 , 3
  %4= add i32 %2, %3
  %5= add i32 0 , 2
  %6= mul i32  -1 , %5
  %7= mul i32 %4, %6
  %8= add i32 0 , 4
  %9= sdiv i32 %7, %8
  store i32 %9, i32* %x
  %10= load i32, i32* %x
  call void @println(i32 %10)
ret i32 0
}
@.strP= private unnamed_addr constant [4 x i8] c"%d\0A\00", align 1
define void @println(i32 %var) {
  %1= call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.strP, i32 0, i32 0), i32 %var)
  ret void
}
declare i32 @printf(i8*, ...)
