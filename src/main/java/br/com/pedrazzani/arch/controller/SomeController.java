package br.com.pedrazzani.arch.controller;

import br.com.pedrazzani.arch.service.SomeService;

public class SomeController {

    public final SomeService someService;

    public SomeController(SomeService someService) {
        this.someService = someService;
    }
}
