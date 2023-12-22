define i32 @main() {
  %x= alloca i32
  %1= add i32 0 , 1
  %2= add i32 0 , 2
  %3= add i32 %1, %2
  store i32 %3, i32* %x
  %4= load i32, i32* %x
  call void @println(i32 %4)
ret i32 0
}
@.strP= private unnamed_addr constant [4 x i8] c"%d\0A\00", align 1
define void @println(i32 %var) {
  %1= call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.strP, i32 0, i32 0), i32 %var)
  ret void
}
declare i32 @printf(i8*, ...)
