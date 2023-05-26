package helpers.employee

import com.study.employeelayoffs.employee.Employee
import org.springframework.core.io.DefaultResourceLoader
import org.springframework.core.io.Resource
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor


class EmployeeBuilder {
    static private final String DEFAULT_EMPLOYEE = "employee/default.yml"
    private Employee employee

    private EmployeeBuilder(Employee employee) {
        this.employee = employee
    }

    static EmployeeBuilder anEmployee() {
        def loader = new DefaultResourceLoader()
        Resource resource = loader.getResource(DEFAULT_EMPLOYEE)
        def parser = new Yaml(new Constructor(Employee.class))
        new EmployeeBuilder(parser.load(resource.inputStream))
    }

    EmployeeBuilder withEmployeeName(String name) {
        this.employee.name = name
        return this
    }

    Employee build() {
        return employee
    }
}
