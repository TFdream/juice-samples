package juice.samples;

import juice.datasource.aop.DynamicDataSourcePointcutAdvisor;
import juice.samples.storage.manager.MemberManager;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * @author Ricky Fung
 */
public class AppTest {

    @Test
    @Ignore
    public void testApp() {
        DynamicDataSourcePointcutAdvisor advisor = new DynamicDataSourcePointcutAdvisor();
        Pointcut pointcut = advisor.getPointcut();

        Class<?> clazz = MemberManager.class;
        System.out.println(pointcut.getClassFilter().matches(clazz));

        Method method = ReflectionUtils.findMethod(clazz, "add", String.class);
        Assert.notNull(method, "方法不存在");

        System.out.println(pointcut.getMethodMatcher().matches(method, clazz));

        method = ReflectionUtils.findMethod(clazz, "findById", Long.class);
        Assert.notNull(method, "方法不存在");

        System.out.println(pointcut.getMethodMatcher().matches(method, clazz));
    }
}
