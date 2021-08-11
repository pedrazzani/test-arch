package br.com.pedrazzani.arch.service.imp;

import br.com.pedrazzani.arch.repository.SomeRepository;
import br.com.pedrazzani.arch.service.SomeService;

public class SomeServiceImp implements SomeService {

    private final SomeRepository someRepository;

    public SomeServiceImp(SomeRepository someRepository) {
        this.someRepository = someRepository;
    }
}
