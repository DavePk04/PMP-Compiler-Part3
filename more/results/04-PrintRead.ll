define i32 @main() {
  %a= alloca i32
  %1= call i32 @readInt()
  store i32 %1, i32* %a
  %2= load i32, i32* %a
  call void @println(i32 %2)
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
