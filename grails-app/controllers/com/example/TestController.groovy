package com.example

import grails.validation.Validateable

// Copied from grails-core.git to reproduce decompile issues
class TestController {
    def methodAction(Person p) {
        [person: p]
    }

    def methodActionWithDate(DateComamndObject co) {
        [command: co]
    }

    private seeIssue13486() {
        // the presence of this local variable could break
        // the compile-time generated no-arg methodActionWithDate()
        // see https://github.com/grails/grails-core/issues/13486
        String co
    }

    def methodActionWithArtist(Artist a) {
        [artist: a]
    }

    def methodActionWithArtistSubclass(ArtistSubclass a) {
        [artist: a]
    }

    def methodActionWithMultipleCommandObjects(Person person, Artist artist)  {
        [person: person, artist: artist]
    }

    def methodActionWithSomeCommand(SomeCommand co) {
        [commandObject: co]
    }

    def methodActionWithWidgetCommand(WidgetCommand widget) {
        [widget: widget]
    }

    def nonDomainCommandObject(NonDomainCommandObjectWithIdAndVersion co) {
        [commandObject: co]
    }

    def methodActionWithGenericBasedCommand(ConcreteGenericBased co) {
        [commandObject: co]
    }

    def zMethodTakingChild(ChildCommand command, long pId) {
        [commandObject: command, pId: pId]
    }

    def methodTakingParent(ParentCommand command) {
        [commandObject: command, pId: 2]
    }
}


class ParentCommand implements Validateable {
    int testId
}

class ChildCommand extends ParentCommand {
    int myId
}

class DateComamndObject {
    Date birthday
}

class WidgetCommand {
    Integer width
    Integer height

    static constraints = { height range: 1..10 }
}

class SomeCommand {
    private String someFieldWithNoSetter

    void setSomeValue(String val) {
        someFieldWithNoSetter = val
    }

    String getSomeValue() {
        someFieldWithNoSetter
    }
}

class Artist implements Validateable {
    String name
    static constraints = { name shared: 'isProg' }
}

class ArtistSubclass extends Artist {
    String bandName
    static constraints = { bandName matches: /[A-Z].*/ }
}

class Person {
    String name
    def theAnswer
    def beforeValidateCounter = 0

    String city
    String state

    def beforeValidate() {
        ++beforeValidateCounter
    }

    static constraints = {
        name matches: /[A-Z]+/
        bindable: false
        city nullable: true, bindable: false
        state nullable: true
    }
}

class NonDomainCommandObjectWithIdAndVersion {
    Long id
    Long version
    String name
}

abstract class WithGeneric<G> implements Validateable {
    String firstName
    G lastName
}

class ConcreteGenericBased extends WithGeneric<String> {
}
