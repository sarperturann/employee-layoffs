package specifications

import spock.lang.Specification

import helpers.employee.EmployeeBuilder

class GetEmployeesSpec extends Specification {
    def "sanity test"() {
        expect:
        1 == 1
    }

    def "should return all the employees"() {
        given:
            def employee = EmployeeBuilder.anEmployee().build()
            layoffActor.createEmployee(employee)
        when:
            def response = layoffActor.getEmployees()
        then:
            response.data.size() == 1
    }
}
