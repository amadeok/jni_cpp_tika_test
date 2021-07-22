
#include <jni.h>
#include "iostream"
#include <stdio.h>

using namespace std;

int main()
{
    JavaVM *jvm;
    JNIEnv *env;
    JavaVMInitArgs vm_args;
    JavaVMOption *options = new JavaVMOption[1];
    options[0].optionString = "-Djava.class.path=.;C:\\Users\\amade\\jni_test_\\target\\MyTest-1.0-SNAPSHOT-jar-with-dependencies.jar";

    vm_args.version = JNI_VERSION_9;
    vm_args.ignoreUnrecognized = JNI_TRUE;
    vm_args.nOptions = 1;
    vm_args.options = options;

    jint rc = JNI_CreateJavaVM(&jvm, (void **)&env, &vm_args);
    if (rc != JNI_OK)
    {
        cin.get();
        exit(EXIT_FAILURE);
    }

    jint ver = env->GetVersion();

    jclass myClass = env->FindClass("src/main/java/MyTest");
    if (myClass == nullptr)
        if (env->ExceptionOccurred())
            env->ExceptionDescribe();
        else
            cout << "clsMain is null but no exception was thrown." << endl;
    else
    {
        jmethodID methodID = env->GetStaticMethodID(myClass, "tika_parse", "(Ljava/lang/String;)Ljava/lang/String;");

        jobject resultObj = env->AllocObject(myClass);

        jstring input_file = env->NewStringUTF("test.txt");

        jstring javaString = (jstring)env->CallObjectMethod(resultObj, methodID, input_file);

        const char *nativeChars = env->GetStringUTFChars(javaString, nullptr);
        printf("%s \n", nativeChars);
    }
    jvm->DestroyJavaVM();
}