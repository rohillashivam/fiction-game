package com.test.fiction.fictiongame.exception.handler;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.test.fiction.fictiongame.exception.AlreadyExistsException;
import com.test.fiction.fictiongame.exception.DuplicatePlayerInAggregateException;
import com.test.fiction.fictiongame.exception.DuplicatePlayerInTeamException;
import com.test.fiction.fictiongame.exception.EmptyDataException;
import com.test.fiction.fictiongame.exception.IncompleteDataException;
import com.test.fiction.fictiongame.exception.InvalidGameException;
import com.test.fiction.fictiongame.exception.InvalidPlayerException;
import com.test.fiction.fictiongame.exception.InvalidTeamException;
import com.test.fiction.fictiongame.exception.PlayerNotMappedInTeamException;
import com.test.fiction.fictiongame.exception.SameTeamInSameMatchException;
import com.test.fiction.fictiongame.exception.UnEqualPlayersInTeamException;
import com.test.fiction.fictiongame.exception.WrongDataException;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	
	private final static Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

	@ExceptionHandler(value= {AlreadyExistsException.class, 
			DuplicatePlayerInAggregateException.class})
	public final ResponseEntity<Object> handleConflictState(RuntimeException ex, 
			WebRequest request) {
        logger.error(ex.getMessage(), ex);
        final String bodyOfResponse = "Already exists resouce";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), 
        		HttpStatus.CONFLICT, request);
    }

	@ExceptionHandler(value= {EmptyDataException.class, WrongDataException.class, PlayerNotMappedInTeamException.class,
			UnEqualPlayersInTeamException.class, SameTeamInSameMatchException.class, IncompleteDataException.class,
			DuplicatePlayerInTeamException.class, InvalidGameException.class, InvalidPlayerException.class,
			InvalidTeamException.class})
	public ResponseEntity<Object> handleBadRequest(RuntimeException ex, WebRequest request) {
		logger.error(ex.getMessage(), ex);
		return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({ NullPointerException.class, IllegalArgumentException.class, IllegalStateException.class,
		HibernateException.class})
    public ResponseEntity<Object> handleInternal(final RuntimeException ex, final WebRequest request) {
        logger.error(ex.getMessage(), ex);
        final String bodyOfResponse = "Unknown error occured on server";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

}
