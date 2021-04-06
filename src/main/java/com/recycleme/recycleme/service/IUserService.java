package com.recycleme.recycleme.service;

import com.recycleme.recycleme.model.Usuario;

import jdk.jshell.spi.ExecutionControl.UserException;

public interface IUserService {

	Usuario cadastrarNovaConta(Usuario usuario) throws UserException;
}
