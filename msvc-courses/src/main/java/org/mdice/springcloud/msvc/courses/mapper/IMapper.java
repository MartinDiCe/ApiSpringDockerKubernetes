package org.mdice.springcloud.msvc.courses.mapper;

public interface IMapper <I, O> {
    public O map(I in);
}