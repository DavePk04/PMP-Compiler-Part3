define i32 @main() {
  %x= alloca i32
  %1= add i32 0 , 0
  store i32 %1, i32* %x
  %y= alloca i32
  %2= add i32 0 , 1
  store i32 %2, i32* %y
  %z= alloca i32
  %3= add i32 0 , 3
  store i32 %3, i32* %z
  %4= load i32, i32* %z
  call void @println(i32 %4)
  %5= load i32, i32* %y
  call void @println(i32 %5)
  %6= load i32, i32* %x
  call void @println(i32 %6)
  %7= add i32 0 , 4
  store i32 %7, i32* %x
  %8= load i32, i32* %x
  call void @println(i32 %8)
ret i32 0
}
@.strP= private unnamed_addr constant [4 x i8] c"%d\0A\00", align 1
define void @println(i32 %var) {
  %1= call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.strP, i32 0, i32 0), i32 %var)
  ret void
}
declare i32 @printf(i8*, ...)
