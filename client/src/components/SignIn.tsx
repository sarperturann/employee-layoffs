import * as React from 'react';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import FormControlLabel from '@mui/material/FormControlLabel';
import Checkbox from '@mui/material/Checkbox';
import Link from '@mui/material/Link';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import axios, { AxiosError } from 'axios';

interface SignInProps {
  isLoggedIn: boolean,
  setIsLoggedIn: React.Dispatch<React.SetStateAction<boolean>>
}

export default function SignIn(props: SignInProps) {

  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    const response = axios.post('api/v1/login', {
      email: data.get('email'),
      password: data.get('password')
    })
    .then(function (resp) {
      console.log(resp)
      props.setIsLoggedIn(true)
    }).catch((reason: AxiosError) => {
      if (reason.response!.status === 400 || reason.response!.status === 404) {
        props.setIsLoggedIn(false)
      }})
  };

  return (
    <Container component="main" maxWidth="xs" >
      <Box
        sx={{
          marginTop: 8,
          display: 'flex',
          flexDirection: 'column',
          alignItems: 'center',
          backgroundColor: '#393945',
          borderRadius:12,
          padding:3
        }}
      >
        <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
          <LockOutlinedIcon />
        </Avatar>
        <Typography sx={{
          color: "#f3f4fa",
        }} component="h1" variant="h5">
          Sign in
        </Typography>
        <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
          <TextField
            margin="normal"
            required
            fullWidth
            id="email"
            label="Email Address"
            name="email"
            autoComplete="email"
            autoFocus
            sx={{
              color: "#f3f4fa",
              backgroundColor: "#f3f4fa"
            }}
          />
          <TextField
            margin="normal"
            required
            fullWidth
            name="password"
            label="Password"
            type="password"
            id="password"
            autoComplete="current-password"
            sx={{
              color: "#f3f4fa",
              backgroundColor: "#f3f4fa"
            }}
          />
          <FormControlLabel
            control={<Checkbox sx={{ color: '#f3f4fa' }} value="remember" color="primary" />}
            label="Remember me"
            sx={{
              color: "#f3f4fa",
            }}
          />
          <Button
            type="submit"
            fullWidth
            variant="contained"
            sx={{ mt: 3, mb: 2, backgroundColor: '#55d8bb', color: "#f3f4fa" }}
          >
            Sign In
          </Button>
          <Grid container>
            <Grid item xs>
              <Link href="#" variant="body2" sx={{
                color: "#f3f4fa",
              }}>
                Forgot password?
              </Link>
            </Grid>
            <Grid item>
              <Link href="#" variant="body2" sx={{
                color: "#f3f4fa",
              }}>
                {"Don't have an account? Sign Up"}
              </Link>
            </Grid>
          </Grid>
        </Box>
      </Box>
    </Container>
  );
}