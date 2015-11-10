package foo


import grails.test.mixin.integration.Integration
import grails.transaction.*
import spock.lang.*

@Integration
class FooSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void setupData(name) {
        new Foo(name: name).save(flush:true)
    }

    // works fine
    void "WhenThen"() {
        when:
        setupData('test')

        then:
        Foo.findByName('test') != null
    }

    // fail with nullpointer
    void "Expect"() {
        given:
        setupData(create_name)

        expect:
        Foo.findByName(create_name).name.equals(test_name)

        where:
        create_name | test_name
        'test'      | 'test'
    }


}
