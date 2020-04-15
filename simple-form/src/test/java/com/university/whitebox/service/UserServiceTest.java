package com.university.whitebox.service;

import static org.mockito.Mockito.times;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.university.whitebox.model.User;
import com.university.whitebox.persistance.UserDao;

import org.junit.Assert;

public class UserServiceTest {

    private static final long USER_ID = 5L;
    private static final String EMAIL = "email";
    private static final String NAME = "name";

    private UserService underTest;
    
    private UserDao userDao;
    
    @Before
    public void before() {
        userDao = Mockito.mock(UserDao.class);
        underTest = new UserService(userDao);
    }
    
    @Test
    public void testCreateUserShouldCreateTheInputUserWhenTheInputIsValid() {
        // Given
        User user = Mockito.mock(User.class);
        Mockito.when(user.getEmail()).thenReturn(EMAIL);
        Mockito.when(user.getName()).thenReturn(NAME);
        Mockito.when(userDao.existsByEmail(EMAIL)).thenReturn(Boolean.FALSE);
        // When
        underTest.createUser(user);
        // Then
        Mockito.verify(userDao).save(user);
        Mockito.verify(userDao).existsByEmail(EMAIL);
        Mockito.verify(user, times(3)).getEmail();
        Mockito.verify(user, times(2)).getName();
        Mockito.verifyNoMoreInteractions(userDao, user);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCreateUserShouldThrowIllegalArgumentExceptionWhenTheInputIsNull() {
        // Given
        User user = null;
        // When
        underTest.createUser(user);
        // Then
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCreateUserShouldThrowIllegalArgumentExceptionWhenEmailIsNull() {
        // Given
        User user = Mockito.mock(User.class);
        Mockito.when(user.getEmail()).thenReturn(null);
        // When
        underTest.createUser(user);
        // Then
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCreateUserShouldThrowIllegalArgumentExceptionWhenEmailIsEmpty() {
        // Given
        User user = Mockito.mock(User.class);
        Mockito.when(user.getEmail()).thenReturn("");
        // When
        underTest.createUser(user);
        // Then
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCreateUserShouldThrowIllegalArgumentExceptionWhenNameIsNull() {
        // Given
        User user = Mockito.mock(User.class);
        Mockito.when(user.getEmail()).thenReturn(EMAIL);
        Mockito.when(user.getName()).thenReturn(null);
        // When
        underTest.createUser(user);
        // Then
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCreateUserShouldThrowIllegalArgumentExceptionWhenNameIsEmpty() {
        // Given
        User user = Mockito.mock(User.class);
        Mockito.when(user.getEmail()).thenReturn(EMAIL);
        Mockito.when(user.getName()).thenReturn("");
        // When
        underTest.createUser(user);
        // Then
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCreateUserShouldThrowIllegalArgumentExceptionWhenEmailIsAlreadyUsed() {
        // Given
        User user = Mockito.mock(User.class);
        Mockito.when(user.getEmail()).thenReturn(EMAIL);
        Mockito.when(user.getName()).thenReturn(NAME);
        Mockito.when(userDao.existsByEmail(EMAIL)).thenReturn(Boolean.TRUE);
        // When
        underTest.createUser(user);
        // Then
    }
    
    @Test
    public void testUpdateUserShouldUpdateTheInputUserWhenTheInputIsValidAndEmailAddressIsOwnedByTheUpdatedUser() {
        // Given
        User user = Mockito.mock(User.class);
        Mockito.when(user.getId()).thenReturn(USER_ID);
        Mockito.when(user.getEmail()).thenReturn(EMAIL);
        Mockito.when(user.getName()).thenReturn(NAME);
        User storedUser = Mockito.mock(User.class);
        Mockito.when(storedUser.getId()).thenReturn(USER_ID);
        Mockito.when(userDao.findByEmail(EMAIL)).thenReturn(Optional.of(storedUser));
        // When
        underTest.updateUser(user);
        // Then
        Mockito.verify(userDao).save(user);
        Mockito.verify(userDao).findByEmail(EMAIL);
        Mockito.verify(user, times(3)).getEmail();
        Mockito.verify(user, times(2)).getName();
        Mockito.verify(user).getId();
        Mockito.verify(storedUser).getId();
        Mockito.verifyNoMoreInteractions(userDao, user, storedUser);
    }
    
    @Test
    public void testUpdateUserShouldUpdateTheInputUserWhenTheInputIsValidAndNewEmailAddressIsNotUsed() {
        // Given
        User user = Mockito.mock(User.class);
        Mockito.when(user.getId()).thenReturn(USER_ID);
        Mockito.when(user.getEmail()).thenReturn(EMAIL);
        Mockito.when(user.getName()).thenReturn(NAME);
        Mockito.when(userDao.findByEmail(EMAIL)).thenReturn(Optional.empty());
        // When
        underTest.updateUser(user);
        // Then
        Mockito.verify(userDao).save(user);
        Mockito.verify(userDao).findByEmail(EMAIL);
        Mockito.verify(user, times(3)).getEmail();
        Mockito.verify(user, times(2)).getName();
        Mockito.verifyNoMoreInteractions(userDao, user);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateUserShouldThrowIllegalArgumentExceptionWhenTheInputIsNull() {
        // Given
        User user = null;
        // When
        underTest.updateUser(user);
        // Then
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateUserShouldThrowIllegalArgumentExceptionWhenEmailIsNull() {
        // Given
        User user = Mockito.mock(User.class);
        Mockito.when(user.getEmail()).thenReturn(null);
        // When
        underTest.updateUser(user);
        // Then
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateUserShouldThrowIllegalArgumentExceptionWhenEmailIsEmpty() {
        // Given
        User user = Mockito.mock(User.class);
        Mockito.when(user.getEmail()).thenReturn("");
        // When
        underTest.updateUser(user);
        // Then
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateUserShouldThrowIllegalArgumentExceptionWhenNameIsNull() {
        // Given
        User user = Mockito.mock(User.class);
        Mockito.when(user.getEmail()).thenReturn(EMAIL);
        Mockito.when(user.getName()).thenReturn(null);
        // When
        underTest.updateUser(user);
        // Then
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateUserShouldThrowIllegalArgumentExceptionWhenNameIsEmpty() {
        // Given
        User user = Mockito.mock(User.class);
        Mockito.when(user.getEmail()).thenReturn(EMAIL);
        Mockito.when(user.getName()).thenReturn("");
        // When
        underTest.updateUser(user);
        // Then
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateUserShouldThrowIllegalArgumentExceptionWhenTheInputIsValidButEmailAddressIsOwnedByAnotherUser() {
        // Given
        User user = Mockito.mock(User.class);
        Mockito.when(user.getId()).thenReturn(USER_ID);
        Mockito.when(user.getEmail()).thenReturn(EMAIL);
        Mockito.when(user.getName()).thenReturn(NAME);
        User storedUser = Mockito.mock(User.class);
        Mockito.when(storedUser.getId()).thenReturn(2L);
        Mockito.when(userDao.findByEmail(EMAIL)).thenReturn(Optional.of(storedUser));
        // When
        underTest.updateUser(user);
        // Then
    }
    
    @Test
    public void testDeleteUserShouldCallUserDaoWhenTheInputIsValid() {
        // Given
        User storedUser = Mockito.mock(User.class);
        Mockito.when(userDao.findById(USER_ID)).thenReturn(Optional.of(storedUser));
        // When
        underTest.deleteUser(USER_ID);
        // Then
        Mockito.verify(userDao).delete(storedUser);
        Mockito.verify(userDao).findById(USER_ID);
        Mockito.verifyNoMoreInteractions(userDao, storedUser);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testDeleteUserShouldThrowIllegalArgumentExceptionWhenTheInputIsNull() {
        // Given
        // When
        underTest.deleteUser(null);
        // Then
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testDeleteUserShouldThrowIllegalArgumentExceptionWhenTheUserToBeDeletedDoesNotExist() {
        // Given
        Mockito.when(userDao.findById(USER_ID)).thenReturn(Optional.empty());
        // When
        underTest.deleteUser(USER_ID);
        // Then
    }
    
    @Test
    public void testGetUserShouldReturnWithTheGivenUserWhenTheInputIsValid() {
        // Given
        User expected = Mockito.mock(User.class);
        Mockito.when(userDao.findById(USER_ID)).thenReturn(Optional.of(expected));
        // When
        User actual = underTest.getUser(USER_ID);
        // Then
        Assert.assertEquals(expected, actual);
        Mockito.verify(userDao).findById(USER_ID);
        Mockito.verifyNoMoreInteractions(userDao, expected);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetUserShouldThrowIllegalArgumentExceptionWhenTheInputIsNull() {
        // Given
        // When
        underTest.getUser(null);
        // Then
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetUserShouldThrowIllegalArgumentExceptionWhenTheUserDoesNotExist() {
        // Given
        Mockito.when(userDao.findById(USER_ID)).thenReturn(Optional.empty());
        // When
        underTest.getUser(USER_ID);
        // Then
    }
    
    @Test
    public void testGetUsersShouldReturnWithTheListOfUsersWhenTheMethodIsCalled() {
        // Given
        Iterable<User> expected = Mockito.mock(Iterable.class);
        Mockito.when(userDao.findAll()).thenReturn(expected);
        // When
        Iterable<User> actual = underTest.getUsers();
        // Then
        Assert.assertEquals(expected, actual);
        Mockito.verify(userDao).findAll();
        Mockito.verifyNoMoreInteractions(userDao, expected);
    }
    
}
