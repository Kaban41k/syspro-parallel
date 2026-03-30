package org.nsu.syspro.parprog.stress.basic;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import org.openjdk.jcstress.infra.runners.ForkedTestConfig;
import org.openjdk.jcstress.infra.collectors.TestResult;
import org.openjdk.jcstress.infra.runners.Runner;
import org.openjdk.jcstress.infra.runners.WorkerSync;
import org.openjdk.jcstress.util.Counter;
import org.openjdk.jcstress.os.AffinitySupport;
import org.openjdk.jcstress.vm.AllocProfileSupport;
import org.openjdk.jcstress.infra.runners.ResourceEstimator;
import org.openjdk.jcstress.infra.runners.VoidThread;
import org.openjdk.jcstress.infra.runners.LongThread;
import org.openjdk.jcstress.infra.runners.CounterThread;
import org.nsu.syspro.parprog.stress.basic.FindStates;
import org.openjdk.jcstress.infra.results.III_Result;

public final class FindStates_jcstress extends Runner<III_Result> {

    volatile WorkerSync workerSync;

    public FindStates_jcstress(ForkedTestConfig config) {
        super(config);
    }

    @Override
    public void sanityCheck(Counter<III_Result> counter) throws Throwable {
        jcstress_sanityCheck_API(counter);
        jcstress_sanityCheck_Resource(counter);
    }

    private static class JcstressThread_APICheck_a extends VoidThread {
        FindStates t;
        FindStates s;
        III_Result r;

        public JcstressThread_APICheck_a(FindStates t, FindStates s, III_Result r) {
            super("JcstressThread_APICheck_a");
            this.t = t;
            this.s = s;
            this.r = r;
        }

        public void internalRun() {
            s.a();
        };

        public void purge() {
            t = null;
            s = null;
            r = null;
        }
    }

    private static class JcstressThread_APICheck_b extends VoidThread {
        FindStates t;
        FindStates s;
        III_Result r;

        public JcstressThread_APICheck_b(FindStates t, FindStates s, III_Result r) {
            super("JcstressThread_APICheck_b");
            this.t = t;
            this.s = s;
            this.r = r;
        }

        public void internalRun() {
            s.b();
        };

        public void purge() {
            t = null;
            s = null;
            r = null;
        }
    }

    private static class JcstressThread_APICheck_c extends VoidThread {
        FindStates t;
        FindStates s;
        III_Result r;

        public JcstressThread_APICheck_c(FindStates t, FindStates s, III_Result r) {
            super("JcstressThread_APICheck_c");
            this.t = t;
            this.s = s;
            this.r = r;
        }

        public void internalRun() {
            s.c();
        };

        public void purge() {
            t = null;
            s = null;
            r = null;
        }
    }

    private void jcstress_sanityCheck_API(Counter<III_Result> counter) throws Throwable {
        final FindStates s = new FindStates();
        final III_Result r = new III_Result();
        VoidThread a0 = new JcstressThread_APICheck_a(null, s, r);
        VoidThread a1 = new JcstressThread_APICheck_b(null, s, r);
        VoidThread a2 = new JcstressThread_APICheck_c(null, s, r);
        a0.start();
        a1.start();
        a2.start();
        a0.join();
        if (a0.throwable() != null) {
            throw a0.throwable();
        }
        a0.purge();
        a1.join();
        if (a1.throwable() != null) {
            throw a1.throwable();
        }
        a1.purge();
        a2.join();
        if (a2.throwable() != null) {
            throw a2.throwable();
        }
        a2.purge();
            s.main(r);
        counter.record(r, 1);
    }

    private static class JcstressThread_ResourceCheck_a extends LongThread {
        FindStates[] ss;
        III_Result[] rs;
        int size;

        public JcstressThread_ResourceCheck_a(FindStates[] ss, III_Result[] rs, int size) {
            super("JcstressThread_ResourceCheck_a");
            this.ss = ss;
            this.rs = rs;
            this.size = size;
        }

        public long internalRun() {
            long a1 = AllocProfileSupport.getAllocatedBytes();
            jcstress_check_a(ss, rs, size);
            long a2 = AllocProfileSupport.getAllocatedBytes();
            return a2 - a1;
        }

        private void jcstress_check_a(FindStates[] ls, III_Result[] lr, int size) {
            for (int c = 0; c < size; c++) {
                ls[c].a();
            }
        }

        public void purge() {
            ss = null;
            rs = null;
        }
    }

    private static class JcstressThread_ResourceCheck_b extends LongThread {
        FindStates[] ss;
        III_Result[] rs;
        int size;

        public JcstressThread_ResourceCheck_b(FindStates[] ss, III_Result[] rs, int size) {
            super("JcstressThread_ResourceCheck_b");
            this.ss = ss;
            this.rs = rs;
            this.size = size;
        }

        public long internalRun() {
            long a1 = AllocProfileSupport.getAllocatedBytes();
            jcstress_check_b(ss, rs, size);
            long a2 = AllocProfileSupport.getAllocatedBytes();
            return a2 - a1;
        }

        private void jcstress_check_b(FindStates[] ls, III_Result[] lr, int size) {
            for (int c = 0; c < size; c++) {
                ls[c].b();
            }
        }

        public void purge() {
            ss = null;
            rs = null;
        }
    }

    private static class JcstressThread_ResourceCheck_c extends LongThread {
        FindStates[] ss;
        III_Result[] rs;
        int size;

        public JcstressThread_ResourceCheck_c(FindStates[] ss, III_Result[] rs, int size) {
            super("JcstressThread_ResourceCheck_c");
            this.ss = ss;
            this.rs = rs;
            this.size = size;
        }

        public long internalRun() {
            long a1 = AllocProfileSupport.getAllocatedBytes();
            jcstress_check_c(ss, rs, size);
            long a2 = AllocProfileSupport.getAllocatedBytes();
            return a2 - a1;
        }

        private void jcstress_check_c(FindStates[] ls, III_Result[] lr, int size) {
            for (int c = 0; c < size; c++) {
                ls[c].c();
            }
        }

        public void purge() {
            ss = null;
            rs = null;
        }
    }

    private static class TestResourceEstimator implements ResourceEstimator {
        final Counter<III_Result> counter;

        public TestResourceEstimator(Counter<III_Result> counter) {
            this.counter = counter;
        }

        public void runWith(int size, long[] cnts) {
            long time1 = System.nanoTime();
            long alloc1 = AllocProfileSupport.getAllocatedBytes();
            FindStates[] ls = new FindStates[size];
            III_Result[] lr = new III_Result[size];
            for (int c = 0; c < size; c++) {
                FindStates s = new FindStates();
                III_Result r = new III_Result();
                lr[c] = r;
                ls[c] = s;
            }
            LongThread a0 = new JcstressThread_ResourceCheck_a(ls, lr, size);
            LongThread a1 = new JcstressThread_ResourceCheck_b(ls, lr, size);
            LongThread a2 = new JcstressThread_ResourceCheck_c(ls, lr, size);
            a0.start();
            a1.start();
            a2.start();
            try {
                a0.join();
                cnts[0] += a0.result();
                a0.purge();
            } catch (InterruptedException e) {
            }
            try {
                a1.join();
                cnts[0] += a1.result();
                a1.purge();
            } catch (InterruptedException e) {
            }
            try {
                a2.join();
                cnts[0] += a2.result();
                a2.purge();
            } catch (InterruptedException e) {
            }
            for (int c = 0; c < size; c++) {
                ls[c].main(lr[c]);
            }
            for (int c = 0; c < size; c++) {
                counter.record(lr[c], 1);
            }
            long time2 = System.nanoTime();
            long alloc2 = AllocProfileSupport.getAllocatedBytes();
            cnts[0] += alloc2 - alloc1;
            cnts[1] += time2 - time1;
        }
    }

    private void jcstress_sanityCheck_Resource(Counter<III_Result> counter) throws Throwable {
        config.adjustStrideCount(new TestResourceEstimator(counter));
    }

    @Override
    public ArrayList<CounterThread<III_Result>> internalRun() {
        int len = config.strideSize * config.strideCount;
        FindStates[] ls = new FindStates[len];
        III_Result[] lr = new III_Result[len];
        for (int c = 0; c < len; c++) {
            ls[c] = new FindStates();
            lr[c] = new III_Result();
        }
        workerSync = new WorkerSync(false, 3, config.spinLoopStyle);

        control.stopping = false;

        if (config.localAffinity) {
            try {
                AffinitySupport.tryBind();
            } catch (Exception e) {
                // Do not care
            }
        }

        ArrayList<CounterThread<III_Result>> threads = new ArrayList<>(3);
        threads.add(new JcstressThread_a(ls, lr, null));
        threads.add(new JcstressThread_b(ls, lr, null));
        threads.add(new JcstressThread_c(ls, lr, null));

        for (CounterThread<III_Result> t : threads) {
            t.start();
        }

        if (config.time > 0) {
            try {
                TimeUnit.MILLISECONDS.sleep(config.time);
            } catch (InterruptedException e) {
            }
        }

        control.stopping = true;

        return threads;
    }

    public static void jcstress_ni_consume_final(Counter<III_Result> cnt, FindStates[] ls, III_Result[] lr, FindStates test, int len, int a) {
        int left = a * len / 3;
        int right = (a + 1) * len / 3;
        for (int c = left; c < right; c++) {
            III_Result r = lr[c];
            FindStates s = ls[c];
            s.main(r);
            cnt.record(r, 1);
        }
    }

    public static void jcstress_consume_reinit(Counter<III_Result> cnt, FindStates[] ls, III_Result[] lr, FindStates test, int len, int a) {
        int left = a * len / 3;
        int right = (a + 1) * len / 3;
        for (int c = left; c < right; c++) {
            III_Result r = lr[c];
            FindStates s = ls[c];
            s.main(r);
            s.x = 0;
            s.y = 0;
            s.z = 0;
            cnt.record(r, 1);
            r.r1 = 0;
            r.r2 = 0;
            r.r3 = 0;
        }
    }

    public class JcstressThread_a extends CounterThread<III_Result> {
        FindStates[] ss;
        III_Result[] rs;
        FindStates test;

        public JcstressThread_a(FindStates[] ss, III_Result[] rs, FindStates test) {
            super("JcstressThread_a");
            this.ss = ss;
            this.rs = rs;
            this.test = test;
        }

        public Counter<III_Result> internalRun() {
            return jcstress_iteration_a();
        }

        private Counter<III_Result> jcstress_iteration_a() {
            int len = config.strideSize * config.strideCount;
            int stride = config.strideSize;
            Counter<III_Result> counter = new Counter<>();
            if (config.localAffinity) AffinitySupport.bind(config.localAffinityMap[0]);
            while (true) {
                WorkerSync sync = workerSync;
                int check = 0;
                for (int start = 0; start < len; start += stride) {
                    jcstress_stride_a(start, start + stride);
                    check += 3;
                    sync.awaitCheckpoint(check);
                }
                if (sync.stopping) {
                    jcstress_ni_consume_final(counter, ss, rs, null, len, 0);
                    return counter;
                } else {
                    jcstress_consume_reinit(counter, ss, rs, null, len, 0);
                }
                if (sync.tryStartUpdate()) {
                    workerSync = new WorkerSync(control.stopping, 3, config.spinLoopStyle);
                }
                sync.postUpdate();
            }
        }

        private void jcstress_stride_a(int start, int end) {
            FindStates[] ls = ss;
            III_Result[] lr = rs;
            for (int c = start; c < end; c++) {
                FindStates s = ls[c];
                s.a();
            }
        }

        public void purge() {
            ss = null;
            rs = null;
            test = null;
        }
    }

    public class JcstressThread_b extends CounterThread<III_Result> {
        FindStates[] ss;
        III_Result[] rs;
        FindStates test;

        public JcstressThread_b(FindStates[] ss, III_Result[] rs, FindStates test) {
            super("JcstressThread_b");
            this.ss = ss;
            this.rs = rs;
            this.test = test;
        }

        public Counter<III_Result> internalRun() {
            return jcstress_iteration_b();
        }

        private Counter<III_Result> jcstress_iteration_b() {
            int len = config.strideSize * config.strideCount;
            int stride = config.strideSize;
            Counter<III_Result> counter = new Counter<>();
            if (config.localAffinity) AffinitySupport.bind(config.localAffinityMap[1]);
            while (true) {
                WorkerSync sync = workerSync;
                int check = 0;
                for (int start = 0; start < len; start += stride) {
                    jcstress_stride_b(start, start + stride);
                    check += 3;
                    sync.awaitCheckpoint(check);
                }
                if (sync.stopping) {
                    jcstress_ni_consume_final(counter, ss, rs, null, len, 1);
                    return counter;
                } else {
                    jcstress_consume_reinit(counter, ss, rs, null, len, 1);
                }
                if (sync.tryStartUpdate()) {
                    workerSync = new WorkerSync(control.stopping, 3, config.spinLoopStyle);
                }
                sync.postUpdate();
            }
        }

        private void jcstress_stride_b(int start, int end) {
            FindStates[] ls = ss;
            III_Result[] lr = rs;
            for (int c = start; c < end; c++) {
                FindStates s = ls[c];
                s.b();
            }
        }

        public void purge() {
            ss = null;
            rs = null;
            test = null;
        }
    }

    public class JcstressThread_c extends CounterThread<III_Result> {
        FindStates[] ss;
        III_Result[] rs;
        FindStates test;

        public JcstressThread_c(FindStates[] ss, III_Result[] rs, FindStates test) {
            super("JcstressThread_c");
            this.ss = ss;
            this.rs = rs;
            this.test = test;
        }

        public Counter<III_Result> internalRun() {
            return jcstress_iteration_c();
        }

        private Counter<III_Result> jcstress_iteration_c() {
            int len = config.strideSize * config.strideCount;
            int stride = config.strideSize;
            Counter<III_Result> counter = new Counter<>();
            if (config.localAffinity) AffinitySupport.bind(config.localAffinityMap[2]);
            while (true) {
                WorkerSync sync = workerSync;
                int check = 0;
                for (int start = 0; start < len; start += stride) {
                    jcstress_stride_c(start, start + stride);
                    check += 3;
                    sync.awaitCheckpoint(check);
                }
                if (sync.stopping) {
                    jcstress_ni_consume_final(counter, ss, rs, null, len, 2);
                    return counter;
                } else {
                    jcstress_consume_reinit(counter, ss, rs, null, len, 2);
                }
                if (sync.tryStartUpdate()) {
                    workerSync = new WorkerSync(control.stopping, 3, config.spinLoopStyle);
                }
                sync.postUpdate();
            }
        }

        private void jcstress_stride_c(int start, int end) {
            FindStates[] ls = ss;
            III_Result[] lr = rs;
            for (int c = start; c < end; c++) {
                FindStates s = ls[c];
                s.c();
            }
        }

        public void purge() {
            ss = null;
            rs = null;
            test = null;
        }
    }

}
