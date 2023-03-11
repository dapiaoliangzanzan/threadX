package com.threadx.visit;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * 修改的execute()方法的访问
 *
 * @author huangfukexing
 * @date 2023/3/9 11:54
 */
public class ModifyThreadPoolExecutorExecuteMethodVisitor extends MethodVisitor {

    protected ModifyThreadPoolExecutorExecuteMethodVisitor(MethodVisitor methodVisitor) {
        super(Opcodes.ASM9, methodVisitor);


    }

    /**
     * 开始方法方法
     */
    @Override
    public void visitCode() {
        super.visitCode();
        try {
            //获取一个System.out的静态变量，将其压入栈顶
            //GETSTATIC java/lang/System.out : Ljava/io/PrintStream;
            mv.visitMethodInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;", false);
            //此时栈顶有一个 PrintStream， 我们需要加载一个常量，将这个常量加载到栈顶
            //LDC "\u6211\u62e6\u622a\u5b8c\u6210\u4e86"
            mv.visitLdcInsn("\u6211\u62e6\u622a\u5b8c\u6210\u4e86");
            //此时，我们调用 PrintStream 对象的 println方法，将栈顶的数据打印出去
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);


        }catch (Throwable w) {
            w.printStackTrace();
        }

    }
}
