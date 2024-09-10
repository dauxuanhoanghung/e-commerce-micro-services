import type { Meta, StoryObj } from '@storybook/react';
import { expect, userEvent, within } from '@storybook/test';

import { AnimatedSubscribeButton } from '@/components/magicui/animated-subscribe-button';

type Story = StoryObj<typeof AnimatedSubscribeButton>;
export default {
  title: 'components/magicui/animated-subscribe-button',
  parameters: {
    // Optional parameter to center the component in the Canvas. More info: https://storybook.js.org/docs/configure/story-layout
    layout: 'centered',
  },
  // This component will have an automatically generated Autodocs entry: https://storybook.js.org/docs/writing-docs/autodocs
  tags: ['autodocs'],
  component: AnimatedSubscribeButton,
  // More on argTypes: https://storybook.js.org/docs/api/argtypes
  argTypes: {
    buttonColor: { control: 'color' },
    buttonTextColor: { control: 'color' },
    subscribeStatus: { control: 'boolean' },
    initialText: { control: 'text' },
    changeText: { control: 'text' },
  },
} as Meta;

export const Subscribe: Story = {
  args: {
    buttonColor: '#4CAF50',
    buttonTextColor: '#FFFFFF',
    subscribeStatus: false,
    initialText: 'Subscribe',
    changeText: 'Subscribed!',
  },
  play: async ({ canvasElement }) => {
    const canvas = within(canvasElement);
    // Check initial state
    const initialButton = canvas.getByRole('button', { name: /Subscribe/i });
    expect(initialButton).toBeInTheDocument();
    await userEvent.click(initialButton);
    // Check state after clicking
    const updatedButton = canvas.getByRole('button', { name: /Subscribed!/i });
    expect(updatedButton).toBeInTheDocument();
    // Click again to unsubscribe
    await userEvent.click(updatedButton);
    // Check if it's back to initial state
    const finalButton = canvas.getByRole('button', { name: /Subscribe/i });
    expect(finalButton).toBeInTheDocument();
  },
};

export const AlreadySubscribed: Story = {
  args: {
    buttonColor: '#4CAF50',
    buttonTextColor: '#FFFFFF',
    subscribeStatus: true,
    initialText: 'Unsubscribe',
    changeText: 'Unsubscribe!',
  },
  play: async ({ canvasElement }) => {
    const canvas = within(canvasElement);
    // Check initial state
    const initialButton = canvas.getByRole('button', { name: /Unsubscribe/i });
    expect(initialButton).toBeInTheDocument();
    await userEvent.click(initialButton);
    // Check state after clicking
    const updatedButton = canvas.getByRole('button', { name: /Unsubscribe/i });
    expect(updatedButton).toBeInTheDocument();
    // Click again to unsubscribe
    await userEvent.click(updatedButton);
    // Check if it's back to initial state
    const finalButton = canvas.getByRole('button', { name: /Unsubscribe!/i });
    expect(finalButton).toBeInTheDocument();
  },
};
