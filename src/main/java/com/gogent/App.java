package com.gogent;

import com.gogent.annotation.Example;
import com.gogent.domain.Description;
import com.gogent.impl.BannerPrinter;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        //打印Banner
        new BannerPrinter("/gogent-banner.txt");
        //扫描指定包下的类，通过注解将相应的实现类信息加载到map中
        Map<String, Description> descriptionMap = scanPackage("com.gogent.impl");
        printHelp(descriptionMap);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String command = scanner.next();
            if (descriptionMap.containsKey(command)) {
                Class<?> clazz = descriptionMap.get(command).getClazz();
                Object instance = clazz.newInstance();
                Method start = clazz.getMethod("start");
                start.invoke(instance);
            } else {
                System.out.println("不能识别输入命令，有效命令如下：");
                printHelp(descriptionMap);
            }
        }
    }

    /**
     * 扫描指定包下的类，通过注解将相应的实现类信息加载到map中
     * @param packagePath 包名
     * @return
     */
    private static Map<String, Description> scanPackage(String packagePath) {
        Map<String, Description> descriptionMap = new HashMap<String, Description>();
        //根目录路径
        String classpath = App.class.getResource("/").getPath();
        //将包名转成路径
        String basePack = packagePath.replace(".", File.separator);
        //组合出需要扫描的资源路径
        String searchPath = classpath + basePack;
        try {
            descriptionMap = scanChild(new File(searchPath), descriptionMap, new File(classpath));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return descriptionMap;
    }

    /**
     * 递归扫描子包以及子包中的类
     * @param child 子包路径
     * @param descriptionMap
     * @param root 根目录
     * @return
     * @throws ClassNotFoundException
     */
    private static Map<String, Description> scanChild(File child, Map<String, Description> descriptionMap, File root) throws ClassNotFoundException {
        if (child.isDirectory()) {
            //如果是文件夹，继续递归往叶子节点扫描
            File[] children = child.listFiles();
            for (File f : children) {
                descriptionMap.putAll(scanChild(f, descriptionMap, root));
            }
        } else {
            //如果是文件，将路径前缀和.class后缀去掉
            String classPath = child.getPath().replace(root.getPath(), "");
            if (classPath.startsWith(File.separator)) {
                classPath = classPath.substring(1).replace(File.separator, ".").replace(".class", "");
            } else {
                classPath = classPath.replace(File.separator, ".").replace(".class", "");
            }
            //通过反射获得类
            Class<?> clazz = Class.forName(classPath);
            //获取类的Example注解
            Example example = clazz.getAnnotation(Example.class);
            if (example != null) {
                //拥有这个注解的类才会被处理
                String[] commands = example.command().split("\\s{0,},\\s{0,}");
                for (String c : commands) {
                    descriptionMap.put(c, new Description(c, example.ref(), example.description()));
                }
            }
        }
        return descriptionMap;
    }

    private static void printHelp(Map<String, Description> discriptionMap) {
        for (String key : discriptionMap.keySet()) {
            System.out.printf("%s\t%s\n", discriptionMap.get(key).getCommand(), discriptionMap.get(key).getDiscription());
        }
    }
}
