# generate protobuf Java code
protoc bytecode.proto --java_out=../tools/src/

# prepend // CHECKSTYLE: OFF in the generated file
ex -sc '1i|// CHECKSTYLE:OFF' -cx  ../tools/src/wyvern/stdlib/support/backend/BytecodeOuterClass.java
