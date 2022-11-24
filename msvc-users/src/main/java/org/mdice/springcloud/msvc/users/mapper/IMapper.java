package org.mdice.springcloud.msvc.users.mapper;

public interface IMapper <I, O> {
    public O map(I in);
}