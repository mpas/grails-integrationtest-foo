package foo


import grails.test.mixin.integration.Integration
import grails.transaction.*
import spock.lang.*

@Integration
@Rollback
class BarSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void setupData(String name) {
        Bar bar = new Bar(name: name).save(flush:true)
    }

    void "Single bar"() {
        when: "create bar"
        setupData('test')

        then:
        Bar.findByName('test') != null
    }

    void "Multiple bar"() {
        given: "create a specific bar"
        setupData(create_name)

        expect: "bar to be present"
        Bar.findByName(create_name).name.equals(check_name)

        where:
        create_name | check_name
        "test"      | "test"
    }


}
