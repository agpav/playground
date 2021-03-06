package org.playground;
import java.util.Set;

public class ThreadSet {

    public static void threads() {
        Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
        for ( Thread t : threadSet){
            System.out.println(t + " state :"+t.getState()+" Deamon :"+ t.isDaemon());
        }
    }


    public static void main(String args[]){
        threads();
    }



//    public static void main(String[] args) {
//
//
//        // Walk up all the way to the root thread group
//        ThreadGroup rootGroup = Thread.currentThread().getThreadGroup();
//        ThreadGroup parent;
//        while ((parent = rootGroup.getParent()) != null) {
//            rootGroup = parent;
//        }
//
//        listThreads(rootGroup, "");
//    }


    // List all threads and recursively list all subgroup
    public static void listThreads(ThreadGroup group, String indent) {
        System.out.println(indent + "Group[" + group.getName() +
                ":" + group.getClass()+"]");
        int nt = group.activeCount();
        Thread[] threads = new Thread[nt*2 + 10]; //nt is not accurate
        nt = group.enumerate(threads, false);

        // List every thread in the group
        for (int i=0; i<nt; i++) {
            Thread t = threads[i];
            System.out.println(indent + "  Thread[" + t.getName()
                    + ":" + t.getClass() + "]");
        }

        // Recursively list all subgroups
        int ng = group.activeGroupCount();
        ThreadGroup[] groups = new ThreadGroup[ng*2 + 10];
        ng = group.enumerate(groups, false);

        for (int i=0; i<ng; i++) {
            listThreads(groups[i], indent + "  ");
        }
    }

}