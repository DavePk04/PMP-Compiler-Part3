# PMP-Compiler-Part3

## Run LLVM IR source codes generated
After running the parser, you can find the LLVM IR source codes generated in the `more/results` folder. 
To run the LLVM IR source codes, you need to install LLVM first. 
You can follow the instructions [here](https://apt.llvm.org/) to install LLVM. 
Then install `Clang` by using the following command:
```shell script
sudo apt-get install clang
```

After installing LLVM, you can run the LLVM IR source codes by using the following command:
```shell script
clang <input_file_name>.ll -o <output_file_name> 
```