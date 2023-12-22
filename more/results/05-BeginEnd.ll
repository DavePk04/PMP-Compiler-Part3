define i32 @main() {
  %z= alloca i32
  %1= add i32 0 , 3
  store i32 %1, i32* %z
  %2= load i32, i32* %z
  call void @println(i32 %2)
ret i32 0
}
@.strP= private unnamed_addr constant [4 x i8] c"%d\0A\00", align 1
define void @println(i32 %var) {
  %1= call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.strP, i32 0, i32 0), i32 %var)
  ret void
}
declare i32 @printf(i8*, ...)
